export interface User {
  id: string;
  username: string;
  email: string;
  createdOn: string
  createdBy: User;
  modifiedOn: string;
  modifiedBy: User;
  disabled: boolean;
}
