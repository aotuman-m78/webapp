package org.zero.repositories;

import org.springframework.cache.annotation.*;
import org.zero.base.repositories.BaseZeroRepository;
import org.zero.model.ZUser;

/**
 * Created by rfang on 2016/8/22.
 */
@CacheConfig(cacheNames = "userCache", cacheManager = "userCacheManager")
public interface ZUserRepository extends BaseZeroRepository<ZUser> {

    @Caching(
        cacheable = {
            @Cacheable(key = "T(org.zero.model.ZUser).buildCacheKey(#p0)")
        },
        evict = {
            @CacheEvict(key = "T(org.zero.model.ZUser).buildCacheKey(#p0)", condition = "#p0.id != null", beforeInvocation = true)
        }
    )
    @Override
    ZUser saveAndFlush(final ZUser user);

    @CacheEvict(key = "T(org.zero.model.ZUser).buildCacheKey(#p0)", beforeInvocation = true)
    @Override
    void delete(final ZUser user);

//    @Cacheable(unless = "#result == null")
    ZUser findByOwnerIdAndName(Long ownerId, String name);
}
