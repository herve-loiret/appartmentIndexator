## if you need a ready to go solr instance, here is how to do :

- run docker-compose build --no-cache && docker-compose up to build the docker image
- docker ps to display the container id
- docker commit 72be5c630590 apindex_solr
- docker save apindex_solr > /tmp/apindex_solr.tar

## on your target client : 
- docker load < /volume1/homes/yoshi/CloudStation/apindex_solr.ta

## notes
- this core is compatible for solr 6.5.1