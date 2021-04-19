local limit = tonumber(ARGV[1]);
local expire = tonumber(ARGV[2]);
local key = KEYS[1];
local current = tonumber(redis.call("get", key) or 0);
if current + 1 > limit then
    return 0;
else
    local res = redis.call("incrby", key, "1");
    if res == 1 then
		redis.call("expire", key, expire);
	end
	return 1;
end