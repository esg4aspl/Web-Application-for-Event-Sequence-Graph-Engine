// @GENERATOR:play-routes-compiler
// @SOURCE:/home/burcu/git/Web-Application-for-Event-Sequence-Graph-Engine/Projects/EventSequenceGraphApplication/conf/routes
// @DATE:Sun Apr 28 22:12:16 EET 2019

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:12
  class ReversePersonController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def sayHelloWithBodyParser(): Call = {
    
      () match {
      
        // @LINE:15
        case ()  =>
          
          Call("POST", _prefix + { _defaultPrefix } + "sayHelloWithBodyParser")
      
      }
    
    }
  
    // @LINE:14
    def sayHelloWithTestPlain(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "sayHelloWithTextPlain")
    }
  
    // @LINE:12
    def sayHello(): Call = {
    
      () match {
      
        // @LINE:12
        case ()  =>
          
          Call("POST", _prefix + { _defaultPrefix } + "sayHello")
      
      }
    
    }
  
  }

  // @LINE:53
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:53
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }

  // @LINE:8
  class ReverseCountController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def count(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "count")
    }
  
  }

  // @LINE:44
  class ReverseBooksController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:47
    def edit(id:Integer): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "books/edit/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("id", id)))
    }
  
    // @LINE:45
    def create(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "books/create")
    }
  
    // @LINE:46
    def show(id:Integer): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "books/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("id", id)))
    }
  
    // @LINE:50
    def destroy(id:Integer): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "books/delete/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("id", id)))
    }
  
    // @LINE:49
    def save(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "books/create")
    }
  
    // @LINE:48
    def update(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "books/edit")
    }
  
    // @LINE:44
    def index(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "books")
    }
  
  }

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
    // @LINE:17
    def welcomeName(name:String, lastName:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "welcome/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("name", name)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("lastName", lastName)))
    }
  
  }

  // @LINE:10
  class ReverseAsyncController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def message(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "message")
    }
  
  }

  // @LINE:19
  class ReverseESGController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:24
    def generateTestCases(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "generateTestCases")
    }
  
    // @LINE:23
    def getESGByName(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "getESGByName")
    }
  
    // @LINE:22
    def getHistoryByESGName(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "getHistoryByESGName")
    }
  
    // @LINE:20
    def saveAs(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "saveAsESG")
    }
  
    // @LINE:19
    def save(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "saveESG")
    }
  
    // @LINE:21
    def getExistESGs(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "findExistESGs")
    }
  
  }


}
