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

Useful types:

- Random within list: give a list with ponderation to generate columns with weighted values.


All types available:
- Full name
- First name
- Last name
- Middle name
- Age
- Email
- Username
- Password
- Gender
- Marital status
- Date of birth
- Phone number
- Cell phone
- Nationality
- Nationality code
- City
- State
- State abbr
- Postal code
- Street address
- Street number
- Full address
- Appartment number
- Card number (visa)
- Expiry date
- Vendor
- Passport number
- Company domain
- Company name
- Company URL
- UUID,
- ISBN10,
- ISBN13,
- Random integer
- Random integer between
- Random string
- Random boolean
- Incremental integer
- Random within list (weighted)
- Current date time
- Current timestamp
- Random date between
- Beer
- Beer style
- Book title
- Book genre
- Book author
- Book publisher
- App name
- App version
- Temperature celcius
- Temperature farhenheit
- Weather
- Animal
- Aircraft
- Airport
- File name
- File extension
- Color name
- Color hex
- Currency code
- Currency name
- Free text

##### Advanced

| Option   | Description                    |
|--------  |--------------------------------|
| Locales  |   Lang & Places (default US)   |
| Seed     | Set a seed to keep same results|
| Time Zone | Time zone to generate dates   |

##### Input in a pipeline

| Option   | Description                    |
|--------  |--------------------------------|
| Pseudo Streaming  |   Subset and Delay    |
| Random Rows Number |   Min & Max rows     |

