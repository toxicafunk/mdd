decoding_json
=============

This plugin receives the changes from WAL and
decodes them to JSON.

Adapted from postgresql-9.4.1/contrib/test_decoding

format
------
    {"type":"transaction.begin","xid":"2010561","committed":"2015-04-22 19:23:35.714443+00"}
    {"type":"table","name":"abc","change":"INSERT","data":{"a":6,"b":7,"c":42}}
    {"type":"table","name":"abc","change":"UPDATE","key":{"a":6,"b":7},"data":{"a":6,"b":7,"c":13}}
    {"type":"table","name":"abc","change":"UPDATE","key":{"a":6,"b":7},"data":{"a":2,"b":7,"c":13}}
    {"type":"table","name":"abc","change":"DELETE","key":{"a":2,"b":7}}
    {"type":"transaction.commit","xid":"2010561","committed":"2015-04-22 19:23:35.714443+00"}

install
-------
  Debian:
    sudo apt-get install postgresql-server-dev-9.4
  Fedora:
    sudo dnf install https://download.postgresql.org/pub/repos/yum/9.6/fedora/fedora-24-x86_64/pgdg-fedora96-9.6-3.noarch.rpm
    sudo dnf install postgresql96-server postgresql96-contrib postgresql96-devel
    (make sure pg_config is in $PATH - /usr/pgsql-9.6/bin/)

    make
    sudo cp decoding_json.so /usr/lib/postgresql/9.4/lib/

configuration of postgresql
---------------------------
see <http://michael.otacoo.com/postgresql-2/postgres-9-4-feature-highlight-basics-logical-decoding/>
