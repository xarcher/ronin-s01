local lockKey =  KEYS[1]
local clientLockRequestId = ARGV[1]

local clientLockIdInRedis = redis.call('GET', lockKey)

if clientLockIdInRedis == clientLockRequestId then
    redis.call('DEL', lockKey)
    return true
else
    return false
end
