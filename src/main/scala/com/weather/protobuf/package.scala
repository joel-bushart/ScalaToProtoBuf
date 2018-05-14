package com.weather

import sbt._
import Keys._
import com.weather.protobuf.transformers.CaseClassTransformer

package object protobuf extends AutoPlugin {

  // by defining autoImport, the settings are automatically imported into user's `*.sbt`
  object autoImport {
    // configuration points, like the built-in `version`, `libraryDependencies`, or `compile`
    val genProtoBuf = taskKey[Seq[File]]("Generate ProtoBuf Files.")
    val genServerCode = settingKey[Boolean]("Generate Server Code")
    val genClientCode = settingKey[Boolean]("Generate Client Code")

    // default values for the tasks and settings
    lazy val baseProtoBufSettings: Seq[Def.Setting[_]] = Seq(
      genProtoBuf := {
        GenProtoBuf(sources.value, (genServerCode in genProtoBuf).value, (genClientCode in genProtoBuf).value)
      },
      genClientCode in obfuscate := false,
      genServerCode in obfuscate := false
    )
  }

  import autoImport._
  override def requires = sbt.plugins.JvmPlugin

  // This plugin is automatically enabled for projects which are JvmPlugin.
  override def trigger = allRequirements

  // a group of settings that are automatically added to projects.
  override val projectSettings =
    inConfig(Compile)(baseProtoBufSettings) ++
      inConfig(Test)(baseProtoBufSettings)


  sourceGenerators in Compile += Def.task {
    val file = (sourceManaged in Compile).value / "protobuf" / "SearchRequest.proto"
    IO.write(file, """syntax = "proto3";
                     |
                     |message SearchRequest {
                     |    string query = 1;
                     |    int32 page_number = 2;
                     |    int32 result_per_page = 3;
                     |}""".stripMargin)
    Seq(file)
  }.taskValue
}

object GenProtoBuf {
  def apply(sources: Seq[File], genServerCode: Boolean, genClientCode: Boolean): Seq[File] = {
    val caseClasses=sources.map{
      file => parseCaseClassTransformer.convertToLabeledGeneric(file)
    }
    //todo Generate protobuf files here.
    val baseGeneratedDir= baseDirectory.value.getAbsolutePath+java.io.File.pathSeparator+"src"+java.io.File.pathSeparator+"generated"+java.io.File.pathSeparator+"protobuf"
    caseClasses.map{ it =>
      val genFile=new File(baseGeneratedDir,it)
    }
    Seq.empty[File]
  }
}
