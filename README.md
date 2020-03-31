# Data Generator
![component build](https://github.com/tgourdel/datagenerator-component/workflows/TCK%20component%20build/badge.svg)

## Description

The Data Generator component has been developed for Talend Pipeline Designer. It is meant to generate random and mock data in a pipeline.

![add_data_generator_dataset](https://user-images.githubusercontent.com/15718239/78036558-b9411800-736a-11ea-97a5-0c6ed55d2093.png)

## Adding to the Remote Engine for Pipelines

```bash
#/bin/bash

mkdir -p /opt/talend/downloads_exchange
re4p_path=/opt/talend/git/cloud-images-automation/modules/re4p/ # Define your RE4P home path
version=$(curl --silent "https://api.github.com/repos/tgourdel/datagenerator-component/releases/latest" | grep -Po '"tag_name": "\K.*?(?=")')

wget -P /opt/talend/downloads_exchange/ https://github.com/tgourdel/datagenerator-component/releases/download/$version/datagenerator-component-$version.car
docker run -v $re4p_path:/opt/talend/pipeline-remote-engine/ -v /opt/talend/downloads_exchange/:/opt/talend/downloads_exchange/ -v /var/run/docker.sock:/var/run/docker.sock tacokit/remote-engine-customizer:latest register-component-archive --remote-engine-dir=/opt/talend/pipeline-remote-engine/ --component-archive=/opt/talend/downloads_exchange/datagenerator-component-$version.car
```

## Dependencies

This component is based on the popular [java-faker](https://github.com/DiUS/java-faker) library.

## ToDo

 - Implement all the functions from the library.
 - Allow pseudo streaming
