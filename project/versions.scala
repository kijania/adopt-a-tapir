object versions {
  val ScalaTest               = "3.2.10"
  val ScalaCheck              = "1.15.4"
  val ScalaTestPlusScalaCheck = "3.2.10.0"
  val ZIO                     = "2.0.2"
  val ZIOInterop              = "3.2.9.1"
  val Http4sServer            = "0.23.12"
  val Http4s                  = "0.23.16"
  val Tapir                   = "1.1.1"
  val Enumeration             = "1.7.0"
  val Quill                   = "4.5.0"
  val PostgreSQL              = "42.5.0"
  val PureConfig              = "0.17.1"
  val TypesafeConfig          = "1.4.2"

  implicit class VersionTransformations(v: String) {
    def majorHyphenMinor: String = v.split("\\.").take(2).mkString("-")
  }
}
