### Quick start
start logstash for local testing
```
./logstash -e 'input {tcp {port=>6666 codec=>json}} output {stdout{}}'
```
you can also use a `nc` for mock server
```
nc -l 6666
```

### How to setup
1. After install ELK environment, you can start with a simple logstash config.
```
input {
  tcp {
    type => "dubbo"
    port => 6666
    codec => json
  }
}
output {
  if [type] == "dubbo" {
    elasticsearch {
      index => "dubbo-%{+YYYY.MM.dd}"
      hosts => "http://127.0.0.1:9200"
  }
}
```
2. Then start your application or our demo.  
Try to start `BasicProvider` and `BasicConsumer`.
3. After a few click on kibana you may create a dashboard like this.  
[dubbo-elk-dashboard.png](screenshots/dubbo-elk-dashboard.png)

### Parameters
You can define logstash monitor like this.
```
<dubbo:monitor address="logstash://127.0.0.1:6666?format=json"/>
```

| Name | Required | Description |
| ---- | :----: |:----:|
| format | no | json or plain, default json |
| ssl | no | use ssl or not, default false |
| keyStorePath | no | key store path for ssl |
| keyStorePass | no | password for key store |
| trustStorePath | no | trust store path for ssl |
| trustStorePass | no | password for trust store |