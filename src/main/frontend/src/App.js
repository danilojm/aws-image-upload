import './App.css';
import React, {useState, useEffect, useCallback} from 'react';
import axios from 'axios';
import {useDropzone} from 'react-dropzone';

const UserProfile = () => {

  const [userProfiles, setUserProfiles] = useState([])

  const fetchUserProfiles = () => {
    axios.get("http://localhost:8080/api/v1/user-profile").then(res => {
      console.log(res)
      setUserProfiles(res.data)
    });
  };    
  useEffect(() => {
    fetchUserProfiles(); 
  }, []);

  return userProfiles.map((userProfile, index) => {
    return(
      <div key={index}>
        <br/>
        <br/>
        <h1>{userProfile.userName}</h1>
        <p>{userProfile.userProfileId}</p>
        <Dropzone></Dropzone>
        <br/>
      </div>

    )
  })

};

function Dropzone() {
  const onDrop = useCallback(acceptedFiles => {
    const file = acceptedFiles[0];
    console.log(file);
  }, [])
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})
  console.log("isDragActive: " + isDragActive)

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the files here ...</p> :
          <p>Drag 'n' drop some files here, or click to select files {isDragActive} </p>          
      }
    </div>
  )
}

function App() {
  return (
    <div className="App">
      <UserProfile></UserProfile>
    </div>
  );
}

export default App;
