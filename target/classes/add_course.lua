local store =   tonumber(redis.call('hget',KEYS[1],'capacity'))
print(store)
if store <= 0
then return 0
end
store = store - 1
redis.call('hset',KEYS[1],'capacity',store)
redis.call('sadd',KEYS[2],ARGV[1])
return 1

