# Homework 12

### Replace env
- “Image: $IMAGE_TAG” in file deployment.yaml
- Replace $IMAGE_TAG by using sed, awk to edit (value: ronin:v0.0.1)

```bash
$ ./helper.sh replace ./data_test/docker-compose.yml '$IMAGE_TAG' ronin:v0.0.1
```

### Open applications 
- Editor
- Browser
- Postman

```bash
$ ./hepler.sh open firefox # chrome, brave, postman, dbeaver... 
```

### Open containers
- Ex: ./container.sh mysql up

```bash
# search container
$ ./helper.sh container search  sql

# lists container
$ ./helper.sh container list

# start container
$ ./helper.sh container start mysql

# stop container
$ ./helper.sh container stop mysql
```