// @GENERATOR:play-routes-compiler
// @SOURCE:/home/burcu/git/Web-Application-for-Event-Sequence-Graph-Engine/ESGApp/conf/routes
// @DATE:Fri Apr 12 00:34:18 EET 2019

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:12
  class ReversePersonController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def sayHelloWithBodyParser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PersonController.sayHelloWithBodyParser",
      """
        function() {
        
          if (true) {
            return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "sayHelloWithBodyParser"})
          }
        
        }
      """
    )
  
    // @LINE:14
    def sayHelloWithTestPlain: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PersonController.sayHelloWithTestPlain",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "sayHelloWithTextPlain"})
        }
      """
    )
  
    // @LINE:12
    def sayHello: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PersonController.sayHello",
      """
        function() {
        
          if (true) {
            return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "sayHello"})
          }
        
        }
      """
    )
  
  }

  // @LINE:52
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:52
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:8
  class ReverseCountController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def count: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CountController.count",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "count"})
        }
      """
    )
  
  }

  // @LINE:43
  class ReverseBooksController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:46
    def edit: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BooksController.edit",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "books/edit/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:44
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BooksController.create",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "books/create"})
        }
      """
    )
  
    // @LINE:45
    def show: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BooksController.show",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "books/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:49
    def destroy: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BooksController.destroy",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "books/delete/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:48
    def save: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BooksController.save",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "books/create"})
        }
      """
    )
  
    // @LINE:47
    def update: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BooksController.update",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "books/edit"})
        }
      """
    )
  
    // @LINE:43
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BooksController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "books"})
        }
      """
    )
  
  }

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:17
    def welcomeName: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.welcomeName",
      """
        function(name0,lastName1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "welcome/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("name", name0)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("lastName", lastName1))})
        }
      """
    )
  
  }

  // @LINE:10
  class ReverseAsyncController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def message: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AsyncController.message",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "message"})
        }
      """
    )
  
  }

  // @LINE:19
  class ReverseESGController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:23
    def getESGByName: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ESGController.getESGByName",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getESGByName"})
        }
      """
    )
  
    // @LINE:22
    def getHistoryByESGName: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ESGController.getHistoryByESGName",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getHistoryByESGName"})
        }
      """
    )
  
    // @LINE:20
    def saveAs: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ESGController.saveAs",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "saveAsESG"})
        }
      """
    )
  
    // @LINE:19
    def save: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ESGController.save",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "saveESG"})
        }
      """
    )
  
    // @LINE:21
    def getExistESGs: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ESGController.getExistESGs",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "findExistESGs"})
        }
      """
    )
  
  }


}
