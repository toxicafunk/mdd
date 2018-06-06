package helpers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.eclipse.emf.mwe.core.WorkflowComponent;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.container.CompositeComponent;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.emf.mwe.internal.core.ast.parser.Location;

/**
 * Este componente copia los recursos estáticos de un cartridge al proyecto destino.
 */
@SuppressWarnings("restriction")
public class CopyResources implements WorkflowComponent {

	private transient Logger log = Logger.getLogger(getClass());
	
	private CompositeComponent container;
	private Location location;
	
	/**
	 * Carpeta donde se va a generar el proyecto
	 */
	private String projectFolder;

	/**
	 * Nombre del proyecto que se va a generar
	 */
	private String projectName;

	/**
	 * Carpeta de recursos que se desea copia al proyecto destino. 
	 * Si no se especifica, se toma "resources" como predeterminada.
	 */
	private String resourcesFolder;
	
	public void setContainer(CompositeComponent container) {
		this.container = container;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Devuelve la ruta de la carpeta donde se va a generar el proyecto
	 * @return La ruta de la carpeta donde se va a generar el proyecto
	 */
	public String getProjectFolder() {
		return projectFolder;
	}

	/**
	 * Establece la ruta de la carpeta donde se va a generar el proyecto
	 * @param projectFolder La ruta de la carpeta donde se va a generar el proyecto
	 */
	public void setProjectFolder(String projectFolder) {
		this.projectFolder = projectFolder;
	}

	/**
	 * Devuelve el nombre del proyecto que se va a generar
	 * @return El nombre del proyecto que se va a generar
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Establece el nombre del proyecto que se va a generar
	 * @param projectName El nombre del proyecto que se va a generar
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getResourcesFolder() {
		return resourcesFolder;
	}

	public void setResourcesFolder(String resourcesFolder) {
		this.resourcesFolder = resourcesFolder;
	}

	public void checkConfiguration(Issues issues) {
	}

	public CompositeComponent getContainer() {
		return container;
	}

	public Location getLocation() {
		return location;
	}

	public void invoke(WorkflowContext context, ProgressMonitor monitor, Issues issues) {
		try {
			File in = null;

			if (resourcesFolder == null)
				in = new File("resources");
			else 
				in = new File(resourcesFolder);
			
			File out = new File(projectFolder + "/" + projectName);
	
			out.mkdirs();
			
			if (in.exists()) {
				File[] files = in.listFiles();
				
				for (int i = 0; i < files.length; i++) {
	
					if (files[i].isDirectory()) {
						copyFolder(files[i], out);
					}
					else {
						copyFile(files[i], new File(out, files[i].getName()));
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Copia un archivo si no existe previamente
	 * @param in Archivo que se desea copiar 
	 * @param out Destino donde se va a copiar el archivo
	 * @throws Exception
	 */
	private void copyFile(File in, File out) throws Exception {
		FileInputStream fis  = new FileInputStream(in);

	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    
	    byte[] buffer = new byte[1024];
	    int i = 0;
	    while((i = fis.read(buffer)) != (-1)) {
	    	baos.write(buffer, 0, i);
	    }
	    fis.close();

	    byte[] data = baos.toByteArray();
	    
        boolean same = false;
	    if (out.exists()) {
	    
		    fis = new FileInputStream(out.getAbsolutePath());
	        baos = new ByteArrayOutputStream();
	        
	        int len = 0;
	        buffer = new byte[1024];
	        
	        while (len != (-1)) {
	        	len = fis.read(buffer, 0, 1024);
	        	if (len != (-1)) baos.write(buffer, 0, len);
	        }
	        
	        fis.close();
	        
	        byte[] oldData = baos.toByteArray();
	
	        if (data.length == oldData.length) {
	        	same = true;
	        	for (i = 0; i < data.length; i++) {
	        		if (data[i] != oldData[i]) {
	        			same = false;
	        			break;
	        		}
	        	}
	        }
	    }
	    
    	if (!same) {
    	    FileOutputStream fos = new FileOutputStream(out);
    		fos.write(data);
    		fos.close();
    		log.info("Copying " + out);
    	}
    	else {
    		log.info("Skipping " + out);
    	}
	}

	/**
	 * Copia todo el contenido de una carpeta de manera recursiva
	 * @param in Carpeta que se desea copiar
	 * @param out Destino donde se va a copiar la carpeta
	 * @throws Exception
	 */
	private void copyFolder(File in, File out) throws Exception {

		// Evitar la copia de archivos de control de los repositorios que utilizamos (CVS y Subversion)
		if (in.isDirectory() && !in.getName().equals("CVS") && !in.getName().equals(".svn")) {

			File newFolder = new File(out, in.getName());
			newFolder.mkdirs();
			
			File[] files = in.listFiles();
			
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					copyFolder(files[i], newFolder);
				}
				else {
					copyFile(files[i], new File(newFolder, files[i].getName()));
				}
			}
		}
    }

	public String getComponentName() {
		return null;
	}
}
