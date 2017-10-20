UUID=$(cat /proc/sys/kernel/random/uuid)
url='http://localhost:8080/api/list/'$UUID
echo $url
data='{"name":"my list","json":"json data"}'
echo $data
curl -H "Content-Type:application/json" -X PUT -d '$data' $url
echo --
