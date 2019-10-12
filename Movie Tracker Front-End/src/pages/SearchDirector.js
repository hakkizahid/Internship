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
            directors:[]
        };
      }

    // componentDidMount(){
    //     axios.get("http://localhost:8080/api/directors/"+{})
    //     .then(res => {
    //         this.setState({users: res.data})        
    //     })
    // }

    clickHandlerDelete = () => {
        axios.delete("http://localhost:8080/api/directors/" + this.state.directors[0].id)
        .then(
            console.log("Item is deleted")
        )
      };
   handleInputChange = event => {
       this.setState({ [event.target.name]: event.target.value });
     };

    clickHandlerSearch2 = () => {
      axios.get("http://localhost:8080/api/directors/" + this.state.search)
      .then(res => {
          this.setState({directors: res.data})        
          console.log(this.state.search)
      })
    };

    TableCreate = () => {
        const table = [];
        for (let index = 0; index < this.state.directors.length; index++) {
                table.push(                    
                    <tr>
                        <td>{this.state.directors[index].name}</td>
                        <td>{this.state.directors[index].surname}</td>
                        <td>{this.state.directors[index].birthDate}</td>
                        <td>{this.state.directors[index].birthPlace}</td>
                        <td>
                            <button className= "buttonUpdate">Update</button>
                        </td>
                        <td>
                            <button className= "buttonDelete">Delete</button>
                        </td>
                    </tr> 
                );
        }
        return table;
    }

    CreateTable = () => {
        return(
            <div className = "outerDiv5">
                <span className="usersTitle" style ={{color: "aliceblue"}}>DIRECTORS</span>
                <table className = "usersTable">
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Username</th>
                        <th>Birth Date</th>
                        <th>Birth Place</th>
                        <th>Delete</th>
                    </tr>
                    <this.TableCreate/>
                </table>
                <br/>
                <br/>
                <Link className = "linkerMovies" to="/directors">Return to Directors</Link>
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