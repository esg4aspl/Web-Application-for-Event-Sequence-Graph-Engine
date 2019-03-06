// @GENERATOR:play-routes-compiler
// @SOURCE:/home/burcu/git/Web-Application-for-Event-Sequence-Graph-Engine/Projects/ESGApp/conf/routes
// @DATE:Wed Mar 06 20:07:21 EET 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
