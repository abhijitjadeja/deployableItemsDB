echo $1 $2 $3
data='{"name":"'
data+=$2
data+='","description":"'
data+=$3
data+='","date":"2017-10-10","status":"R"}'
echo $data
curl -H "Content-Type:application/json" -X POST -d $data http://localhost:8080/api/release/$1
echo --
