# SpringBoot-AI-Solutions
Java Spring AI Tech Stack Solutions


### AWS Bedrock Spring AI Implementation 

- This application runs at Port 8092
- http://localhost:8092/swagger-ui/index.html#

```shell
curl -X 'POST' \
  'http://localhost:8092/sb-ai/aws/bedrock/v1/chat/hello' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "userMessage": "string"
}'
```
