# Brunel Visualization [![Build Status](https://travis-ci.org/Brunel-Visualization/Brunel.svg?branch=master)](https://travis-ci.org/Brunel-Visualization/Brunel)

## Zero to Visualization in Sixty Seconds

* [Try it out online](http://brunel.mybluemix.net/gallery_app/renderer?title=Bubble+chart+of+2000+games&brunel_src=data%28%27http%3A%2F%2Fwillsfamily.org%2Ffiles%2Fvis%2Fdata%2FBGG+Top+2000+Games.csv%27%29+bubble+color%28rating%29+size%28voters%29+sort%28rating%29+label%28title%29+tooltip%28title%2C+%23all%29+legends%28none%29+style%28%27*+%7Bfont-size%3A+7pt%7D%27%29&description=A+simple+bubble+chart+showing+the+top+ranked+games.+The+color+shows+the+BGG+rating+and+the+size+of+each+bubble+represents+the+number+of+voters+for+that+game.+The+data+is+already+sorted+by+rank%2C+so+no+sort+was+needed.+Data+is+from+March+2015) (and add your own data)
* View the [gallery](https://github.com/Brunel-Visualization/Brunel/wiki)
* Check out the [Brunel Visualization Cookbook](https://github.com/Brunel-Visualization/Brunel/wiki/Brunel-Visualization-Cookbook)
* Use the interactive [language tutorial](http://brunel.mybluemix.net/docs)
* Download the [latest build](https://github.com/Brunel-Visualization/Brunel/releases)

## Details

The Brunel Visualization project defines a highly succinct and novel language that defines interactive data visualizations based on tabular data.  The language is well suited for both data scientists and more aggressive business users.  The system interprets the language and produces visualizations using the user's choice of existing lower-level visualization technologies typically used by application engineers such as RAVE or D3 .  It can operate stand-alone and integrated into iPython notebooks with further integrations as well as other low-level rendering support depending on the desires of the community.

For Jupyter notebook integrations, Brunel Visualization supplies a REST API interprets the Brunel Visualization language and generates the required Javascript to display the visualization in a web browser.  Any app server that supports JAX-RS should work.  Simply deploy the brunel `.war` file.  Both  [Apache TomEE w/JAX-RS](http://tomee.apache.org/apache-tomee.html) and [IBM Liberty](https://developer.ibm.com/wasdev/downloads/) are known to work.  Instructions for setting up Brunel Visualization using TomEE are below.  We are working on improving and generalizing this.

For direct Java integrations, see:  `D3Builder` for creating D3 output, `ControlWriter` for adding interactive controls and `WebDisplay` as an example using these.  `BrunelService` also contains a usage example.

## Sub-Projects and Folders
* `core` Primary support for Brunel Visualization
* `data` The Brunel Visualization data processing engine
* `etc`  Examples and supporting utilities
* `lib`  Dependent .jars
* `service`  A basic web service to provide Brunel Visualization output
* `python`  Integration of Brunel Visualization for Jupyter (IPython)
* `R` Integration of Brunel Visualization for Jupyter (R)
* `gallery` Source code for the Brunel Gallery web application

## Builds

### Build using Gradle

#### Dependencies
* Install [Apache TomEE w/JAX-RS](http://tomee.apache.org/apache-tomee.html)
* Install [Gradle](https://gradle.org/).
* Install Python 3 and also install `nose` using `pip install nose` (used for unit tests during build)

#### Set local environment variables:

    BRUNEL_SERVER=http://localhost:8080/brunel-service
    TOMCAT_HOME=[root dir of TomEE]

#### For a complete build:

```gradle build```

This will build all Java (including the '.war' file), run all tests, create Javadocs, collect all required Javascript and place all of this into `/brunel/out`

Note: `gradle assemble` can be used to produce only the Java `.jar` files and likely does not require TomEE or Python to be installed.  The resulting .jars will appear in the `/build` folder for each Java project.

#### To start the Brunel server

Gradle can be used to deploy Brunel to TomEE and start the web server:

```gradle cargoRunLocal```

To confirm it is working, try calling the service:

    http://localhost:8080/brunel-service/brunel/interpret/d3?brunel_src=data('http%3A%2F%2Fbrunel.mybluemix.net%2Fsample_data%2FBGG%2520Top%25202000%2520Games.csv')%20chord%20x(categories)%20y(playerage)%20color(playerage)%20size(%23count)%20tooltip(%23all)&width=575&height=575

#### Next:

* Explore the docs to learn more
* Run `VisualTests` located in `/etc` to view a few Brunel syntax test examples
* See the `/python` and `/R` folders for instructions on how to use Brunel with Jupyter (IPython)
* For Java integrations, explore the javadocs generated by the Gradle build in `/out`

#### Details regarding JS/CSS files and locations:

Brunel uses a mix of static, translated and generated Javascript.

* Non-translated JS/CSS for Brunel is in `/core`.  These are expected to be edited by developers.
* The translated `BrunelData.js` is created during the build of the `data` project.  Do not edit this file.
* `VisualTests.java` (in `/etc`) will copy the JS/CSS upon execution so they are in the expected locations
* Builds for `/service` will copy the JS/CSS in the correct locations for the .war file.  Additionally, the task `copyWebFiles` copies the JS/CSS in the expected place for execution directly from the src.
* Builds for `/python`, will copy the JS/CSS into a folder that also contains JS that is specific for the Jupyter integration
