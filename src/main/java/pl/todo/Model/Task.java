package pl.todo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private UUID uuid;
    private String name;
    private String description;
    private Instant startTaskTime;
    private Instant endTaskTime;
    private Instant createdOn;

    private Task() {
    }

    private Task(Builder builder) {
        this.uuid = builder.uuid;
        this.name = builder.name;
        this.description = builder.description;
        this.startTaskTime = builder.startTaskTime;
        this.endTaskTime = builder.endTaskTime;
        this.createdOn = builder.createdOn;
    }

    public long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Instant getStartTaskTime() {
        return startTaskTime;
    }

    public Instant getEndTaskTime() {
        return endTaskTime;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public static class Builder {
        private UUID uuid;
        private String name;
        private String description;
        private Instant startTaskTime;
        private Instant endTaskTime;
        private Instant createdOn;


        public Builder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder startTaskTime(Instant startTaskTime) {
            this.startTaskTime = startTaskTime;
            return this;
        }

        public Builder endTaskTime(Instant endTaskTime) {
            this.endTaskTime = endTaskTime;
            return this;
        }

        public Builder createdOn(Instant createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startTaskTime=" + startTaskTime +
                ", endTaskTime=" + endTaskTime +
                ", createdOn=" + createdOn +
                '}';
    }
}
