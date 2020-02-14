if redis.call('sismember',KEYS[1],ARGV[1])==1
then redis.call('srem',KEYS[1],ARGV[1])
else return 0
end


local store = tonumber(redis.call('hget',KEYS[2],'capacity'))
store = store + 1
redis.call('hset',KEYS[2],'capacity',store)
return 1
