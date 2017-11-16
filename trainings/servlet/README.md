**Apache Banchmark Test:**
`ab -n 10000 -c 50 http://localhost:8080/ServletTask-1.0/countries`
```
This is ApacheBench, Version 2.3 <$Revision: 1706008 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 1000 requests
Completed 2000 requests
Completed 3000 requests
Completed 4000 requests
Completed 5000 requests
Completed 6000 requests
Completed 7000 requests
Completed 8000 requests
Completed 9000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:        Jetty(9.2.14.v20151106)
Server Hostname:        localhost
Server Port:            8080

Document Path:          /ServletTask-1.0/countries
Document Length:        2427 bytes

Concurrency Level:      50
Time taken for tests:   0.878 seconds
Complete requests:      10000
Failed requests:        0
Non-2xx responses:      10000
Total transferred:      26250000 bytes
HTML transferred:       24270000 bytes
Requests per second:    11394.82 [#/sec] (mean)
Time per request:       4.388 [ms] (mean)
Time per request:       0.088 [ms] (mean, across all concurrent requests)
Transfer rate:          29210.35 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.2      0       2
Processing:     0    4   3.2      3      37
Waiting:        0    4   3.2      3      37
Total:          0    4   3.2      4      37

Percentage of the requests served within a certain time (ms)
  50%      4
  66%      4
  75%      5
  80%      6
  90%      8
  95%     10
  98%     15
  99%     18
 100%     37 (longest request)
```

**GETTING LIST OF COUNTRIES:**

curl -X GET http://localhost:8080/ServletTask-1.0/countries

| Answers                                                  |
-----------------------------------------------------------|
| {"code":200,"message":"OK"}                              |
| {"code":404,"message":"Not found"}                       |

**CREATING COUNTRY:**

curl -I -H "code: LNR" -H "name: Luganska Narodna Respublika" -H "continent: Europe" -H "region: East-Ukraine" -H "surfacearea: 1.2" -H "population: 500000" -H "localname: Lugansk" -H "governmentForm: republic" -H "code2: LR" -X PUT http://localhost:8080/ServletTask-1.0/countries

| Answers                                                  |
-----------------------------------------------------------|
| {"code":200,"message":"OK"}                              |
| {"code":400,"message":"Bad Request"}                     |

**DELETING COUNTRY:**

curl -I -H "countrycode: LNR" -X DELETE http://localhost:8080/ServletTask-1.0/countries

| Answers                                                  |
-----------------------------------------------------------|
| {"code":200,"message":"OK"}                              |
| {"code":404,"message":"Not found"}                       |
| {"code":400,"message":"Bad Request"}                     |

**SELECTING COUNTRY BY CODE:**

curl -I -H "countrycode: USA" -X GET http://localhost:8080/ServletTask-1.0/countries

| Answers                                                  |
-----------------------------------------------------------|
| {"code":200,"message":"OK"}                              |
| {"code":404,"message":"Not found"}                       |
| {"code":400,"message":"Bad Request"}                     |