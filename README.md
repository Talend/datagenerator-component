# Data Generator
![component build](https://github.com/tgourdel/datagenerator-component/workflows/TCK%20component%20build/badge.svg)

## Description

The Data Generator component has been developed for Talend Pipeline Designer. It is meant to generate random and mock data in a pipeline.

![add_dataset_data_generator](https://user-images.githubusercontent.com/15718239/78035943-040e6000-736a-11ea-8bfe-286c27b5c20f.png)

## Adding to the Remote Engine for Pipelines

```bash
#/bin/bash

mkdir -p /opt/talend/downloads_exchange
re4p_path=/opt/talend/git/cloud-images-automation/modules/re4p/ # Define your RE4P home path
version=$(curl --silent "https://api.github.com/repos/tgourdel/datagenerator-component/releases/latest" | grep -Po '"tag_name": "\K.*?(?=")')

wget -P /opt/talend/downloads_exchange/ https://github.com/tgourdel/datagenerator-component/releases/download/$version/datagenerator-component-$version.car
docker run -v $re4p_path:/opt/talend/pipeline-remote-engine/ -v /opt/talend/downloads_exchange/:/opt/talend/downloads_exchange/ -v /var/run/docker.sock:/var/run/docker.sock tacokit/remote-engine-customizer:latest register-component-archive --remote-engine-dir=/opt/talend/pipeline-remote-engine/ --component-archive=/opt/talend/downloads_exchange/datagenerator-component-$version.car
```
