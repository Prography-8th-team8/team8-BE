package prography.cakeke.server.store.adpter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import prography.cakeke.server.store.domain.StoreAndTag;

public interface StoreAndTagRepository extends JpaRepository<StoreAndTag, Long> {
    List<StoreAndTag> findByStoreIdIn(List<Long> ids);
}
