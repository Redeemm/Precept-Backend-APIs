package com.example.Precept.Backend.APIs.Settings.registration.token.StaffToken;

import com.example.Precept.Backend.APIs.modeller.People.Staff.Staff;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "staff_confirmation_tokens")
public class StaffConfirmationToken {

    @Id
    @SequenceGenerator(
            name = "staff_confirmation_token_sequence",
            sequenceName = "staff_confirmation_token_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "staff_confirmation_token_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_staff_id"
    )
    private Staff staff;

    public StaffConfirmationToken(String token,
                                  LocalDateTime createdAt,
                                  LocalDateTime expiresAt,
                                  Staff staff) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.staff = staff;
    }
}
