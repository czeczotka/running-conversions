# running-conversions

### what is it?

`running-conversions` is a Scala library which enables some running-related calculations. As part of my preparations for different races I have been following training programmes and frequently ended up using good old pen and paper to calculate pace, time and distance. Therefore, I decided to have a pet project which would make these calculations easy while I could enjoy learning Scala. Hope you will find it helpful.

### how can I run it?
 * [Install Scala](http://www.scala-lang.org/download/install.html), you may also want to [install sbt](http://www.scala-sbt.org/0.13/docs/Setup.html)
 * download the running-conversions jar (`running-conversions_2.11-0.1.jar`) or build it with `sbt package`
 * start REPL with the jar in the classpath 
```
scala -cp running-conversions_2.11-0.1.jar
```
 * import `postfixOps` and relevant objects
```
import scala.language.postfixOps, com.czeczotka.conversion.running.Time._, com.czeczotka.conversion.running.Distance._
```
 * use REPL, i.e:
```
scala> k10 in "39m30s" pace
res0: com.czeczotka.conversion.running.Pace = 3:57
```
 * alternatively you can edit and run `com.czeczotka.conversion.running.RunningConversions` object (i.e. with  `sbt run`) or use `running-conversions` in your own code

### how can I use it?

 * create an activity which would represent a distance over time or a distance at a pace 
 * query the activity for details such as time, pace, distance or splits

### what does it do?
 * for a given distance and pace calculate the finish time (create an activity and get the time)
```
input:  marathon at "5:41" time 
output: 3h59m48s
```
```
input:  metres400 at "4:15" time 
output: 1m42s
```
 * for a given distance and time calculate the pace (create an activity and get the pace)
```
input:  k10 in "49m00s" pace 
output: 4:54
```
```
input:  metres800 in "3m28s" pace 
output: 4:20
```
 * get every kilometre splits for an activity
```
input:  k10 in "44m30s"  splits
output: Map(1.0 -> 4m27s, 2.0 -> 8m54s, 3.0 -> 13m21s, 4.0 -> 17m48s, 5.0 -> 22m15s, 6.0 -> 26m42s, 7.0 -> 31m09s, 8.0 -> 35m36s, 9.0 -> 40m03s, 10.0 -> 44m30s)
```
 * get custom splits for an activity (i.e.: every 5k)
```
// calculate 5k splits to run the marathon in 3 hours 45 minutes
input:  marathon in "3h45m" splits k5
output: Map(5.0 -> 26m35s, 10.0 -> 53m10s, 15.0 -> 1h19m45s, 20.0 -> 1h46m20s, 25.0 -> 2h12m55s, 30.0 -> 2h39m30s, 35.0 -> 3h06m05s, 40.0 -> 3h32m40s)
```
```
input:  marathon in "3h45m" splits k5 foreach println
// calculate 5k splits to run the marathon in 3 hours 45 minutes and print them in new lines
(5.0,26m35s)
(10.0,53m10s)
(15.0,1h19m45s)
(20.0,1h46m20s)
(25.0,2h12m55s)
(30.0,2h39m30s)
(35.0,3h06m05s)
(40.0,3h32m40s)
``` 
```
// calculate 400m splits to run 2000m in 8 minutes
input:  metres2000 in "8m00s" splits metres400
output: Map(0.4 -> 1m36s, 0.8 -> 3m12s, 1.2 -> 4m48s, 1.6 -> 6m24s, 2.0 -> 8m00s)
```

### input and output conventions
 * at the moment only metric system is supported 
 * pace is in minutes per km written as `<minutes>:<seconds>`, i.e.: `5:15` is 5 min and 15 sec.
 * time is written as `<hours>h<minutes>m<seconds>s` or `<hours>h<minutes>m` or `<minutes>m<seconds>s`, i.e: `1h10m15s`, `50m50s`, `2h59m`
 * both infix and dot notations are supported
```
dot:   halfMarathon.in("2h00m").pace
infix: halfMarathon in "2h00m"  pace
```

### predefined distances
See the `com.czeczotka.conversion.running.Distance` object for the full list:
 * 400 metres (`metres400`)
 * 600 metres (`metres600`)
 * 800 metres (`metres800`)
 * 1000 metres (`metres1000`)
 * 1200 metres (`metres1200`)
 * 1600 metres (`metres1600`)
 * 2000 metres (`metres2000`)
 * 2000 metres (`metres2000`)
 * 2000 metres (`metres2000`)
 * 1k (`k1`)
 * 5k (`k5`)
 * 10k (`k10`)
 * 1 mile (`mile1`)
 * 10 miles (`mile10`)
 * half marathon (`halfMarathon`)
 * marathon (`marathon`)

### custom distances
You can create custom distances with these utility methods (metres, km, kilometres, miles):
```
input:  kilometres(12.5)
output: Distance(12500)
```
```
input:  miles(8)
output: Distance(12874)
```