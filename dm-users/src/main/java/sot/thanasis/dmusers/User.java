package sot.thanasis.dmusers;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;
    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;
    @ManyToOne
    @JoinColumn(name = "modified_by_id")
    private User modifiedBy;
    @Column(name = "disabled")
    private boolean disabled;

}
