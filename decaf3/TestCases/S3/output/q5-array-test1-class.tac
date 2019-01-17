VTABLE(_A) {
    <empty>
    A
    _A.seta;
    _A.printA;
}

VTABLE(_Main) {
    <empty>
    Main
}

FUNCTION(_A_New) {
memo ''
_A_New:
    _T3 = 8
    parm _T3
    _T4 =  call _Alloc
    _T5 = 0
    *(_T4 + 4) = _T5
    _T6 = VTBL <_A>
    *(_T4 + 0) = _T6
    return _T4
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T7 = 4
    parm _T7
    _T8 =  call _Alloc
    _T9 = VTBL <_Main>
    *(_T8 + 0) = _T9
    return _T8
}

FUNCTION(_A.seta) {
memo '_T0:4 _T1:8'
_A.seta:
    _T10 = *(_T0 + 4)
    *(_T0 + 4) = _T1
}

FUNCTION(_A.printA) {
memo '_T2:4'
_A.printA:
    _T11 = *(_T2 + 4)
    parm _T11
    call _PrintInt
    _T12 = "\n"
    parm _T12
    call _PrintString
}

FUNCTION(main) {
memo ''
main:
    _T15 =  call _A_New
    _T14 = _T15
    _T16 = 1
    parm _T14
    parm _T16
    _T17 = *(_T14 + 0)
    _T18 = *(_T17 + 8)
    call _T18
    _T19 = 10
    parm _T14
    parm _T19
    _T20 = *(_T14 + 0)
    _T21 = *(_T20 + 8)
    call _T21
    _T22 = 6
    _T23 = 0
    _T24 = (_T22 < _T23)
    if (_T24 == 0) branch _L13
    _T25 = "Decaf runtime error: The length of the created array should not be less than 0.\n"
    parm _T25
    call _PrintString
    call _Halt
_L13:
    _T26 = 4
    _T27 = (_T26 * _T22)
    _T28 = (_T26 + _T27)
    parm _T28
    _T29 =  call _Alloc
    *(_T29 + 0) = _T22
    _T30 = 0
    _T29 = (_T29 + _T28)
_L14:
    _T28 = (_T28 - _T26)
    _T31 = (_T30 < _T28)
    if (_T31 == 0) branch _L15
    _T29 = (_T29 - _T26)
    _T32 =  call _A_New
    *(_T29 + 0) = _T32
    _T33 = *(_T14 + 4)
    *(_T32 + 4) = _T33
    branch _L14
_L15:
    _T13 = _T29
    _T34 = 1
    _T35 = *(_T13 - 4)
    _T36 = (_T34 < _T35)
    if (_T36 == 0) branch _L16
    _T37 = 0
    _T38 = (_T34 < _T37)
    if (_T38 == 0) branch _L17
_L16:
    _T39 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T39
    call _PrintString
    call _Halt
_L17:
    _T40 = 4
    _T41 = (_T34 * _T40)
    _T42 = (_T13 + _T41)
    _T43 = *(_T42 + 0)
    _T44 = 15
    parm _T43
    parm _T44
    _T45 = *(_T43 + 0)
    _T46 = *(_T45 + 8)
    call _T46
    _T47 = 0
    _T48 = *(_T13 - 4)
    _T49 = (_T47 < _T48)
    if (_T49 == 0) branch _L18
    _T50 = 0
    _T51 = (_T47 < _T50)
    if (_T51 == 0) branch _L19
_L18:
    _T52 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T52
    call _PrintString
    call _Halt
_L19:
    _T53 = 4
    _T54 = (_T47 * _T53)
    _T55 = (_T13 + _T54)
    _T56 = *(_T55 + 0)
    parm _T56
    _T57 = *(_T56 + 0)
    _T58 = *(_T57 + 12)
    call _T58
    _T59 = 1
    _T60 = *(_T13 - 4)
    _T61 = (_T59 < _T60)
    if (_T61 == 0) branch _L20
    _T62 = 0
    _T63 = (_T59 < _T62)
    if (_T63 == 0) branch _L21
_L20:
    _T64 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T64
    call _PrintString
    call _Halt
_L21:
    _T65 = 4
    _T66 = (_T59 * _T65)
    _T67 = (_T13 + _T66)
    _T68 = *(_T67 + 0)
    parm _T68
    _T69 = *(_T68 + 0)
    _T70 = *(_T69 + 12)
    call _T70
}

