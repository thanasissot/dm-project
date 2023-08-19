import { Component } from '@angular/core';
import {UserService} from "../../core/services/user.service";
import {User} from "../../core/model/user.model";
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent {
  displayedColumns: string[] = ['id', 'username', 'email', 'disabled'];
  users: User[] = [];
  showUserForm: boolean = false;
  userForm: FormGroup;

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
  ) {
    this.userForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
    });
  }

  ngOnInit() {
    this.populateUserList();
  }

  onSave(): void {
    if (this.userForm.valid) {
      const newUser = {
        username: this.userForm.value.username,
        email: this.userForm.value.email,
      };

      this.userService.createUser(newUser).subscribe({
        next: (body) => {
          console.log('user created.');
          // request updated user data
          this.populateUserList();
        },
        error: (msg: any) => {
          console.log(msg);
        }
      });

      // Handle saving the new user object here
      console.log('New user object:', newUser);

      // Reset the form and hide it
      this.userForm.reset();
      this.showUserForm = false;
    }
  }

  onCancel(): void {
    // Reset the form and hide it
    this.userForm.reset();
    this.showUserForm = false;
  }

  populateUserList() {
    this.userService.getUsers().subscribe(
      body => {
        this.users = Object.values(body);
      }
    );
  }

}


