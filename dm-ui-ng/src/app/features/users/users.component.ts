import { Component } from '@angular/core';
import {UserService} from "../../core/services/user.service";
import {User} from "../../core/model/user.model";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent {
  private users: User[] = [];
  constructor(
    private userService: UserService,
  ) {
  }

  ngOnInit() {
    this.userService.getUsers().subscribe(
      body => {
        this.users = Object.values(body);
      }
    )
  }
}
