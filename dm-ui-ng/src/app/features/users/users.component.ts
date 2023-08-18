import { Component } from '@angular/core';
import {UserService} from "../../core/services/user.service";
import {User} from "../../core/model/user.model";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent {
  public displayedColumns: string[] = ['id', 'username', 'email', 'disabled'];
  public users: User[] = [];

  constructor(
    private userService: UserService,
  ) { }

  ngOnInit() {
    this.userService.getUsers().subscribe(
      body => {
        this.users = Object.values(body);
        this.users.push(sampleUser);
      }
    );

    const sampleUser: User = {
      id: '1',
      username: 'john_doe',
      email: 'john@example.com',
      createdOn: '2023-08-19T10:00:00Z',
      createdBy: {
        id: '2',
        username: 'admin',
        email: 'admin@example.com',
        createdOn: '2023-08-18T08:30:00Z',
        createdBy: null as any,
        modifiedOn: '2023-08-18T08:30:00Z',
        modifiedBy: null as any,
        disabled: false
      },
      modifiedOn: '2023-08-19T15:45:00Z',
      modifiedBy: {
        id: '3',
        username: 'manager',
        email: 'manager@example.com',
        createdOn: '2023-08-19T09:15:00Z',
        createdBy: null as any,
        modifiedOn: '2023-08-19T09:15:00Z',
        modifiedBy: null as any,
        disabled: false
      },
      disabled: false
    };


  }
}


