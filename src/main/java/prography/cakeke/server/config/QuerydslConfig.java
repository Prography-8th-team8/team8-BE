package prography.cakeke.server.config;

import static com.querydsl.jpa.JPQLTemplates.DEFAULT_ESCAPE;

import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mysema.commons.lang.CloseableIterator;
import com.mysema.commons.lang.IteratorAdapter;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.QueryHandler;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Configuration
public class QuerydslConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(
                new JPQLTemplates(DEFAULT_ESCAPE, new QueryHandler() {

                    @Override
                    public void addEntity(Query query, String alias, Class<?> type) {
                        // do nothing
                    }

                    @Override
                    public void addScalar(Query query, String alias, Class<?> type) {
                        // do nothing
                    }

                    @Override
                    public boolean createNativeQueryTyped() {
                        return true;
                    }

                    @SuppressWarnings("unchecked")
                    @Override
                    public <T> CloseableIterator<T> iterate(Query query,
                                                            final FactoryExpression<?> projection) {
                        Stream<T> stream = stream(query, projection);
                        return new IteratorAdapter<>(stream.iterator(), stream::close);
                    }

                    @Override
                    public <T> Stream<T> stream(Query query, FactoryExpression<?> projection) {
                        final Stream resultStream = query.getResultStream();
                        if (projection != null) {
                            return resultStream.map(element -> projection.newInstance(
                                    (Object[]) (element.getClass().isArray() ? element :
                                                new Object[] { element })));
                        }
                        return resultStream;
                    }

                    @Override
                    public boolean transform(Query query, FactoryExpression<?> projection) {
                        return false;
                    }

                    @Override
                    public boolean wrapEntityProjections() {
                        return false;
                    }

                }) {
                }, entityManager);
    }
}
