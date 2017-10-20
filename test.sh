echo $1 $2 $3
data='{"name":"'
echo $data
data=$data$2
echo $data
data=$data'","description":"'$3
echo $data
data=$data'","date":"2017-10-10"}'
echo $data
url='http://localhost:8080/api/release/'$1
echo $url
curl -H "Content-Type:application/json" -X PUT -d $data $url
echo --
