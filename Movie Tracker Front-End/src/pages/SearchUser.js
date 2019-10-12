import React from "react";
import axios from 'axios';
import {Link} from 'react-router-dom';
import "../pagesCss/SearchUser.css";
import { Route, NavLink, Switch } from "react-router-dom";

class SearchUser extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            search:"",
            users:[]
        };
      }

    // componentDidMount(){
    //     axios.get("http://localhost:8080/api/users/"+{})
    //     .then(res => {
    //         this.setState({users: res.data})        
    //     })
    // }

    clickHandlerDelete = () => {
        axios.delete("http://localhost:8080/api/users/" + this.state.users[0].id)
        .then(
            console.log("Item is deleted")
        )
      };
   handleInputChange = event => {
       this.setState({ [event.target.name]: event.target.value });
     };

    clickHandlerSearch2 = () => {
      axios.get("http://localhost:8080/api/users/" + this.state.search)
      .then(res => {
          this.setState({users: res.data})        
          console.log(this.state.search)
      })
    };

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
                        <td>
                            <button className= "buttonDelete" onClick = {this.clickHandlerDelete}>Delete</button>
                        </td>
                    </tr> 
                );
        }
        return table;
    }

    CreateTable = () => {
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
                        <th>Delete</th>
                    </tr>
                    <this.TableCreate/>
                </table>
                <br/>
                <br/>
                <Link className = "linkerMovies" to="/users">Return to Users</Link>
                <br/>
                <Link className = "linkerUsers" to="/main">Return to Main Page</Link>
            </div>
        );
    }

    render(){
        return(
            <div className = "outerDiv3"><br/>
                <div className="outerDiv3">
                    <span className ="usersTitle" style ={{color: "aliceblue"}}>SEARCH</span>
                    <br/>
                    <input className="textL"
                    type="text"
                    name="search"
                    value={this.state.search}
                    onChange={this.handleInputChange}
                    />
                    <br />
                    <button className="buttonStyle" onClick={this.clickHandlerSearch2}>Search</button>
                </div>
                <br/>
                <br/>
                <this.CreateTable/>
            </div>
        );
    }
}

export default SearchUser;