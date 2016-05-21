# running-conversions

### what is it?

`running-conversions` is a Scala library which enables some running-related calculations. As part of my preparations different races I have been following training programmes frequently ended up using good old pen and paper to calculate pace, time and distance. Therefore, I decided to have a pet project which would make these calculations easy while I could enjoy learning Scala.

### how can I run it?

### what does it do?
 * for a given distance and pace calculate the finish time
```
input:  marathon at "5:41" time 
output: 3h59m48s
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

### input and output conventions
 * at the moment only metric system is supported 
 * pace is in m/km written as <minutes>:<seconds>, i.e.: `5:15` is 5 min and 15 sec.
 * time is written as <hours>h:<minutes>m:<seconds>s or <hours>h:<minutes>m or <minutes>m:<seconds>s, i.e: `1h10m15s`, `50m50s`, `2h59m`
