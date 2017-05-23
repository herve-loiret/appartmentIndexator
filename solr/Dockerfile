FROM solr:6.5
ADD conf/. /opt/solr/server/solr/appindex/conf/
USER root
RUN chown -R solr:solr /opt/solr/server/solr/appindex/
USER solr