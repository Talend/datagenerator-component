<h1 align="center">
  Data Generator
</h1>
<div align="center">

   <a href="https://github.com/Talend/datagenerator-component/actions">
       <img src="https://github.com/Talend/datagenerator-component/workflows/TCK%20component%20build/badge.svg"/>
   </a>
   <a href="http://www.apache.org/licenses/LICENSE-2.0.html">
       <img src="http://img.shields.io/:license-apache-brightgreen.svg"/>
   </a>
   
___

📦 [Download latest CAR archive](https://github.com/Talend/datagenerator-component/releases/latest/download/datagenerator-component.car)
</div>

## Description

The Data Generator component has been developed for Talend Pipeline Designer. It is meant to generate random and mock data in a pipeline.

## Adding to the Remote Engine for Pipelines

```bash
#/bin/bash

mkdir -p /opt/talend/downloads_exchange
re4p_path=/opt/talend/git/cloud-images-automation/modules/re4p/ # Define your RE4P home path
wget -P /opt/talend/downloads_exchange/ https://github.com/Talend/datagenerator-component/releases/latest/download/datagenerator-component.car
docker run -v $re4p_path:/opt/talend/pipeline-remote-engine/ -v /opt/talend/downloads_exchange/:/opt/talend/downloads_exchange/ -v /var/run/docker.sock:/var/run/docker.sock tacokit/remote-engine-customizer:latest register-component-archive --remote-engine-dir=/opt/talend/pipeline-remote-engine/ --component-archive=/opt/talend/downloads_exchange/datagenerator-component.car
```

## Dependencies

This component is based on the popular [java-faker](https://github.com/DiUS/java-faker) library.

> This library is a port of Ruby's faker gem (as well as Perl's Data::Faker library) that generates fake data. It's useful when you're developing a new project and need some pretty data for showcase.

## Component configuration

Once a **Data Generator** connection have been created you can set up a dataset:

##### Main

| Option   | Description                    |
|--------  |--------------------------------|
| Rows     |   Number of rows to generate   |
| Fields   | List of fields (name and type) |

##### Advanced

| Option   | Description                    |
|--------  |--------------------------------|
| Locales  |   Lang & Places (default US)   |
| Seed     | Set a seed to keep same results|

##### Input in a pipeline

| Option   | Description                    |
|--------  |--------------------------------|
| Pseudo Streaming  |   Subset and Delay    |

## 📝 ToDo

 - Implement all the functions from the library.
 - Add place / coordinates functions
 - ~~Implement pseudo streaming~~ ✔
