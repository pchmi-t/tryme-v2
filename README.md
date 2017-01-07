# Try-Me - FMI distributed system project

[![Build Status](https://app.snap-ci.com/pchmi-t/tryme-v2/branch/master/build_image)](https://app.snap-ci.com/pchmi-t/tryme-v2/branch/master)

This is a small application written as a FMI distributed systems project.
The application runs on [Heroku](http://heroku.com).

## Installation

### Eclipse

1. Import the project in Eclipse from File -> Import. ... -> Existing Maven Projects
2. Run As -> Maven build
3. `clean install` to just build the project
4. `clean install -P heroku` to deploy the project to Heroku