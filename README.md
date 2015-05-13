# Swagger Play2 Module

[![Build Status](https://travis-ci.org/markusjura/swagger-play2.svg?branch=master)](https://travis-ci.org/markusjura/swagger-play2)

## Overview
This is a module adds support for [Play 2]((http://www.playframework.org) projects. It is written in Scala but can be used with either Java- or Scala-based Play applications.

## Dependencies

* Play 2.3.9
* Scala 2.11.6
* SBT 0.13.8
 
Usage
-----

You can depend on pre-built libraries in maven central by adding the following dependency:

```
resolvers += Resolver.bintrayRepo("markusjura", "maven")

libraryDependencies += "com.markusjura" %% "swagger-play2" % "1.3.7"
```

### Adding Swagger to your Play2 app

There are just a couple steps to integrate your Play2 app with swagger.

1.  Add the resource listing to your routes file:

  ```
  GET     /api-docs.json        controllers.ApiHelpController.getResources
  ``` 

2. Annotate the Play actions in your controller with Swagger annotations. This allows the Swagger framework to create the [api-declaration](https://github.com/swagger-api/swagger-core/wiki/Annotations-1.5.X#resource-api-declaration) automatically:

  ```scala
  @Api(value = "/auth", description = "Authentication operations")
  object AuthController extends SecuredController {

    @ApiOperation(
      value = "Authenticates the user.",
      notes = "Authenticates the user with a given password. Valid usernames are `user-1` to `user-10`. The password is `pass`",
      nickname = "auth",
      httpMethod = "POST")
    @ApiImplicitParams(Array(
        new ApiImplicitParam(name = "body", value = "JSON body must contain a username and password.", required = false,
          dataType = "application/json", paramType = "body", defaultValue = authBodyDefaultValue)))
      @ApiResponses(Array(
        new ApiResponse(code = 200, message = "Username and password is valid. Returns a token. The token contains the encrypted username."),
        new ApiResponse(code = 400, message = "Username or password not supplied."),
        new ApiResponse(code = 401, message = "Password is incorrect."),
        new ApiResponse(code = 401, message = "Username doesn't exist.")))
    def auth = Action.async { request =>
      ...
    }
  }  
  ```

What this does is the following:

* Tells swagger that the methods in this controller should be described under the `/api/auth` path
* The Routes file tells swagger that this API listens to `/auth`
* Describes the operation as a `GET` with the documentation `Authenticates the user.` with more detailed notes `Authenticates the user..`
* Takes the `body` as `application/json`
* Returns error codes 200, 400 and 401, with the messages provided

In the routes file, you then wire this api as follows:

  ```
  # API Documentation
  # Swagger - Root Resources Listing
  GET     /                           controllers.ApiDocController.view
  GET     /api                        controllers.ApiHelpController.getResources
  GET     /api/auth                   controllers.ApiHelpController.getResource(path = "/auth")
  ```

This will "attach" the /api-docs.json/pet api to the swagger resource listing, and the method to the `getPetById` method above

#### The ApiParam annotation

Swagger for play has two types of `ApiParam`s--they are `ApiParam` and `ApiParamImplicit`.  The distinction is that some
paramaters (variables) are passed to the method implicitly by the framework.  ALL body parameters need to be described
with `ApiParamImplicit` annotations.  If they are `queryParam`s or `pathParam`s, you can use `ApiParam` annotations.

### Sample Application

Please take a look [here](https://github.com/markusjura/eplay-auth) for a simple sample application using the Swagger Play2 module. 
