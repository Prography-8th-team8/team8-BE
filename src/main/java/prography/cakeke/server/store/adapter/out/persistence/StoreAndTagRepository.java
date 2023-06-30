package prography.cakeke.server.store.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import prography.cakeke.server.store.domain.StoreAndTag;

public interface StoreAndTagRepository extends JpaRepository<StoreAndTag, Long> {

    StoreAndTag save(StoreAndTag storeAndTag);
}
