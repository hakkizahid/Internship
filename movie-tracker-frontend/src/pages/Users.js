import React from "react";
import axios from 'axios';
import {Link} from 'react-router-dom';
import "../pagesCss/Users.css";

class Users extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
        users:[]
        };
      }

    componentDidMount(){
        axios.get("http://localhost:8080/api/users")
        .then(res => {
            this.setState({users: res.data})        
        })
    }

    TableCreate = () => {
        const table = [];
        for (let index = 0; index < this.state.users.length; index++) {
                table.push(                    
                    <tr>
                        <td>{this.state.users[index].role}</td>
                        <td>{this.state.users[index].name}</td>
                        <td>{this.state.users[index].surname}</td>
                        <td>{this.state.users[index].username}</td>
                        <td>{this.state.users[index].email}</td>
                        <td>
                            <button className= "buttonUpdate">Update</button>
                        </td>
                    </tr> 
                );
        }
        return table;
    }

    render(){
        return(
            <div className = "outerDiv5">
                <span className="usersTitle" style ={{color: "aliceblue"}}>USERS</span>
                <table className = "usersTable">
                    <tr>
                        <th>Role</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Username</th>
                        <th>E-mail</th>
                        <th>Update</th>
                    </tr>
                    <this.TableCreate/>
                </table>
                <br/>
                <Link className = "addlinkerMovies" to="/adduser">ADD USER</Link>
                <br/>
                <br/>
                <Link className = "addlinkerMovies" to="/searchuser">SEARCH USER</Link>
                <br/>
                <br/>
                <Link className = "linkerUsers" to="/main">Return to Main Page</Link>
            </div>
        );
    }
}

export default Users;