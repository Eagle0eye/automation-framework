    package models;


    import lombok.Builder;
    import lombok.Data;

    import java.time.Instant;
    import java.util.Set;

    @Data
    @Builder
    public class SessionCache {
        private String email;
        private UserInfo accountInfo;
        private Set<ProductCache> cartItems;
        private Instant createdAt;
        private Instant lastUpdated;

    }
