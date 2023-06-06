import { Route, Routes } from 'react-router-dom';
import './App.css';
import CreateStudent from './components/CreateStudent';
import Student from './components/Student';

function App() {
  return (
    <>
    <Routes>
      <Route path='/' element={<Student />} />
      <Route path='/create' element={<CreateStudent />} />
    </Routes>
    </>
  );  
}

export default App;
