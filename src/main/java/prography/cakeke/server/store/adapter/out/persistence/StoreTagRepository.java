package prography.cakeke.server.store.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import prography.cakeke.server.store.domain.StoreTag;
import prography.cakeke.server.store.domain.StoreType;

public interface StoreTagRepository extends JpaRepository<StoreTag, Long> {
    Optional<StoreTag> findByStoreType(StoreType storeType);
}
