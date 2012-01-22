resolvers += "Web plugin repo" at "http://siasia.github.com/maven2"

resolvers += "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"

resolvers += Classpaths.typesafeResolver

resolvers += Resolver.url("Typesafe repository", new java.net.URL("http://typesafe.artifactoryonline.com/typesafe/ivy-releases/"))(Resolver.defaultIvyPatterns)

addSbtPlugin("com.github.siasia" %% "xsbt-web-plugin" % "0.1.2")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "0.11.0")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse" % "1.4.0")
