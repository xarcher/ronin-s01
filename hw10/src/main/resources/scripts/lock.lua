local lockKey = KEYS[1]
local clientLockRequestId = ARGV[1]
local expireTime = tonumber(ARGV[2])

local clientLockIdInRedis = redis.call('GET', lockKey)

-- Not exist
if (clientLockIdInRedis == nil or (type(clientLockIdInRedis) == "boolean" and not clientLockIdInRedis)) then
    redis.call('SET', lockKey, clientLockRequestId, 'PX', expireTime)
    return true
elseif clientLockIdInRedis == clientLockRequestId then -- continue lock
    redis.call('PEXPIRE', lockKey, expireTime)
    return true
else
    return false
end