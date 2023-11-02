package prography.cakeke.server.store.application.port.out;

public interface SaveRedisPort {
    String save(String key, String value);
}
