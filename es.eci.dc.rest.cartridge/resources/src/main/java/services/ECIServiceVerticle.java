package services;

import java.sql.Connection;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import providers.postgresql.PostgresqlProvider;

public abstract class ECIServiceVerticle extends AbstractVerticle {
		
	final Logger logger = LoggerFactory.getLogger(ECIServiceVerticle.class);
	
	@Override
	public void start() throws Exception {
		
		String address = config().getString("address");
		logger.info("Deployed service for " + address);
		vertx.eventBus().consumer(address, message -> {
			Connection connection = null;
			UserTransaction ut = PostgresqlProvider.getUserTransaction();
			
			try {
				connection = PostgresqlProvider.getConnection(config());
				
				DSLContext create = DSL.using(connection, SQLDialect.POSTGRES_9_4);
				ut.begin();
				executeService(ut, create, message);
			}
			catch (Exception e) {
				logger.error("error", e);
				try {
					ut.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					logger.error("error", e1);
				}
				message.reply(new JsonObject("{\"errors\": [\"Error no controlado\"]}"));
			}
			finally {
				if (connection != null) {
					try {
						connection.close();
						try {
							switch (ut.getStatus()) {
							case Status.STATUS_ACTIVE:
							case Status.STATUS_UNKNOWN:
								try {
									ut.commit();
								} catch (SecurityException | IllegalStateException | HeuristicMixedException
										| HeuristicRollbackException e) {
									logger.error("error", e);
									message.reply(new JsonObject("{\"errors\": [\"Error al cerrar transaccion\"]}"));
								}
							default:
								break;
							}
						} catch (SystemException e) {
							logger.error("error", e);
						}
					}
					catch (Exception e) {
						logger.error("error", e);
						message.reply(new JsonObject("{\"errors\": [\"Error cerrando coneccion\"]}"));
					}
				}
			}
		});
	}
	
	protected abstract void executeService(UserTransaction ut, DSLContext create, Message<Object> message)
			throws NotSupportedException, SystemException;
}
