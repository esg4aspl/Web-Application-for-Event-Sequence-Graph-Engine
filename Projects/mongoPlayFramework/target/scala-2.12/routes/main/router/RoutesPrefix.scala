// @GENERATOR:play-routes-compiler
// @SOURCE:/home/burcu/eclipse-workspace/mongoPlayFramework/conf/routes
// @DATE:Tue Dec 04 12:30:01 EET 2018


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
