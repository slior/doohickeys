

@echo off
@echo Creating directories in %1...
mkdir %1\project
mkdir %1\src\main\scala
mkdir %1\src\test\scala


@echo Creating build.sbt ...
@echo libraryDependencies += "org.scalatest" %% "scalatest_2.11" %% "2.2.4" %% "test" > %1\build.sbt

@echo Creating plugins.sbt ...
@echo addSbtPlugin("com.typesafe.sbteclipse" %% "sbteclipse-plugin" %% "3.0.0") > %1\project\plugins.sbt