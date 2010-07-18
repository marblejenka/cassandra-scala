:load jarloader.scala

import org.apache.cassandra.thrift._
import org.apache.thrift._
import org.apache.thrift.protocol._
import org.apache.thrift.transport._

val transport = new TSocket("localhost", 9160)
val protocol = new TBinaryProtocol(transport)

transport open
val client = new Cassandra.Client(protocol)

val key = "sample1"
val colmunName = "hoge"
val value = "sample value"
val timestamp = System.currentTimeMillis
val columnPath = new ColumnPath("Standard1")  
columnPath setColumn(colmunName.getBytes)

client insert("Keyspace1", key, columnPath, value.getBytes, timestamp ,ConsistencyLevel.ONE)

transport.flush
transport.close

