package prography.cakeke.server.store.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import prography.cakeke.server.store.domain.StoreAndTag;

public interface StoreAndTagRepository extends JpaRepository<StoreAndTag, Long> {

    @Modifying
    @Query("delete from StoreAndTag sat where sat.store.id = :storeId")
    void deleteByStoreId(Long storeId);
}
