FROM solr:6.5
ADD appindex/. /opt/solr/server/solr/appindex
USER root
RUN chown -R solr:solr /opt/solr/server/solr/appindex/
USER solr